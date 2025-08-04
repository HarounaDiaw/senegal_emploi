import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';

import { OffreEmploiService } from 'app/entities/offre-emploi/service/offre-emploi.service';
import { CandidatService, Candidat } from 'app/entities/candidat/service/candidat.service';
import { AccountService } from 'app/core/auth/account.service';

import { CommonModule } from '@angular/common';

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
    private accountService: AccountService,
  ) {
    this.profilForm = this.fb.group({
      nom: [''],
      prenom: [''],
      email: [''],
      telephone: [''],
      adresse: [''],
      sexe: [''],
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
        });
        this.photoUrl = data.user.imageUrl ?? null;
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
}
