import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { Subject, takeUntil } from 'rxjs';
import SharedModule from 'app/shared/shared.module';
import { MainComponent } from '../layouts/main/main.component';
import { FooterComponent } from '../layouts/footer/footer.component';

@Component({
  selector: 'jhi-home',
  standalone: true,
  imports: [SharedModule, MainComponent, FooterComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export default class HomeComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  private accountService = inject(AccountService);
  private router = inject(Router);

  ngOnInit(): void {
    this.accountService.getAuthenticationState().pipe(takeUntil(this.destroy$)).subscribe();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  navigateToRegister(): void {
    this.router.navigate(['/account/register'], {
      // Options de navigation supplémentaires si nécessaire
      skipLocationChange: false, // Garde l'URL synchronisée
      state: { fromHome: true }, // Exemple de données supplémentaires
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
