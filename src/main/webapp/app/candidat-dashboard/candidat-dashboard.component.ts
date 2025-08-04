import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';

import { OffreEmploiService } from 'app/entities/offre-emploi/service/offre-emploi.service';
import { CandidatService, Candidat } from 'app/entities/candidat/service/candidat.service';
import { AccountService } from 'app/core/auth/account.service';

import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'jhi-candidat-dashboard',
  templateUrl: './candidat-dashboard.component.html',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  styleUrls: ['./candidat-dashboard.component.scss'],
})
export class CandidatDashboardComponent implements OnInit {
  profilForm: FormGroup;

  offres: any[] = [];
  candidat: Candidat | null = null;
  photoUrl: string | null = null;
  cvFile: File | null = null;
  searchQuery = '';
  isEditingProfil = false;

  toggleProfilForm(): void {
    this.isEditingProfil = !this.isEditingProfil;
  }

  constructor(
    private fb: FormBuilder,
    private offreService: OffreEmploiService,
    private candidatService: CandidatService,
    private sanitizer: DomSanitizer,
    private http: HttpClient,
    private accountService: AccountService,
  ) {
    this.profilForm = this.fb.group({
      nom: [''],
      prenom: [''],
      email: [''],
      telephone: [''],
      adresse: [''],
      sexe: [''],
      photo: [''],
      cv: [''],
    });
  }

  ngOnInit(): void {
    this.loadOffres();
    this.loadCandidat();
    this.candidatService.getCurrent().subscribe(candidat => {
      this.profilForm.patchValue(candidat);
    });
  }

  loadCandidat(): void {
    this.candidatService.getCurrent().subscribe({
      next: data => {
        this.candidat = data;
        this.profilForm.patchValue({
          nom: data.user.lastName,
          prenom: data.user.firstName,
          email: data.user.email,
          telephone: data.telephone,
          adresse: data.adresse,
          sexe: data.sexe,
          photo: data.photo,
          cv: data.cv,
        });
        this.photoUrl = data.photo ? 'content/images/candidats/photo/' + data.photo : null;
      },
      error: err => {
        console.error('Erreur de chargement du candidat :', err);
      },
    });
  }

  onPhotoSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.photoUrl = URL.createObjectURL(file);
    }
  }

  onCvSelected(event: any): void {
    this.cvFile = event.target.files[0];
  }

  saveProfil(): void {
    if (!this.profilForm.valid) return;

    const updatedData = this.profilForm.value;

    this.candidatService.updateCurrent(updatedData).subscribe({
      next: res => {
        alert('Profil mis à jour avec succès');
        this.isEditingProfil = false;
        this.loadCandidat();
        console.log('Candidat mis à jour :', res);
      },
      error: err => {
        console.error('Erreur lors de la mise à jour du profil :', err);
        alert('Erreur lors de la mise à jour du profil');
      },
    });
  }

  loadOffres(): void {
    this.offreService.query().subscribe(res => {
      this.offres = res.body ?? [];
    });
  }

  postuler(offreId: number): void {
    // TODO : appeler un endpoint POST /postuler avec offreId
    alert(`Postulation envoyée pour l'offre ${offreId}`);
  }

  filtrerOffres(): void {
    // TODO : ajouter la recherche dans le backend si nécessaire
    this.loadOffres();
  }

  onFileSelect(event: Event, type: 'photo' | 'cv'): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    const endpoint = type === 'photo' ? '/api/candidats/upload-photo' : '/api/candidats/upload-cv';

    this.http.post(endpoint, formData, { responseType: 'text' }).subscribe({
      next: (filename: string) => {
        if (type === 'photo') {
          this.profilForm.patchValue({ photo: filename });
          this.photoUrl = 'content/images/candidats/photo/' + filename;
        } else {
          this.profilForm.patchValue({ cv: filename });
        }

        // ✅ Appel immédiat de la mise à jour du profil après upload
        this.saveProfil();
      },
      error: err => console.error('Échec de l’upload', err),
    });
  }
  getInitiales(): string {
    const nom = this.profilForm.get('nom')?.value || '';
    const prenom = this.profilForm.get('prenom')?.value || '';
    return (prenom[0] || '') + (nom[0] || '');
  }
}
