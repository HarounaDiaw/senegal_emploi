import { AfterViewInit, Component, ElementRef, inject, signal, viewChild } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/config/error.constants';
import SharedModule from 'app/shared/shared.module';
import PasswordStrengthBarComponent from '../password/password-strength-bar/password-strength-bar.component';
import { RegisterService } from './register.service';

@Component({
  selector: 'jhi-register',
  imports: [SharedModule, RouterModule, FormsModule, ReactiveFormsModule, PasswordStrengthBarComponent],
  templateUrl: './register.component.html',
})
export default class RegisterComponent implements AfterViewInit {
  login = viewChild.required<ElementRef>('login');

  doNotMatch = signal(false);
  error = signal(false);
  errorEmailExists = signal(false);
  errorUserExists = signal(false);
  success = signal(false);

  registerForm = new FormGroup({
    login: new FormControl('', {
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
      ],
    }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email],
    }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(4), Validators.maxLength(50)],
    }),
    confirmPassword: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(4), Validators.maxLength(50)],
    }),

    // Ajout du type d'utilisateur
    type: new FormControl<'CANDIDAT' | 'RECRUTEUR'>('CANDIDAT', { nonNullable: true }),

    // Champs communs au candidat
    telephone: new FormControl('', { nonNullable: true }),
    adresse: new FormControl('', { nonNullable: true }),
    sexe: new FormControl<'Masculin' | 'Féminin'>('Masculin', { nonNullable: true }),
    photo: new FormControl('', { nonNullable: true }),

    // Champs spécifiques au recruteur
    entreprise: new FormControl(''),
    secteur: new FormControl(''),
  });

  private readonly registerService = inject(RegisterService);

  ngAfterViewInit(): void {
    this.login().nativeElement.focus();
  }

  register(): void {
    this.doNotMatch.set(false);
    this.error.set(false);
    this.errorEmailExists.set(false);
    this.errorUserExists.set(false);

    // Récupération des valeurs du formulaire
    const formValues = this.registerForm.getRawValue();

    if (formValues.password !== formValues.confirmPassword) {
      this.doNotMatch.set(true);
      return;
    }

    // Construction dynamique du payload
    const Info: any = {
      login: formValues.login,
      email: formValues.email,
      password: formValues.password,
      langKey: 'fr',
      type: formValues.type,
      telephone: formValues.telephone,
      adresse: formValues.adresse,
      sexe: formValues.sexe,
      photo: formValues.photo,
    };

    if (formValues.type === 'RECRUTEUR') {
      Info.entreprise = formValues.entreprise ?? '';
      Info.secteur = formValues.secteur ?? '';
    }

    this.registerService.save(Info).subscribe({
      next: () => this.success.set(true),
      error: response => this.processError(response),
    });
  }

  private processError(response: HttpErrorResponse): void {
    if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
      this.errorUserExists.set(true);
    } else if (response.status === 400 && response.error.type === EMAIL_ALREADY_USED_TYPE) {
      this.errorEmailExists.set(true);
    } else {
      this.error.set(true);
    }
  }
}
