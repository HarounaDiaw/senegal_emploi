import { Component } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
@Component({
  selector: 'jhi-footer',
  standalone: true,
  imports: [FontAwesomeModule, RouterModule],
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent {
  constructor(private router: Router) {}
  currentYear: number = new Date().getFullYear();
  navigateToOffreEmploiList() {
    this.router.navigate(['/offre-emploi']); // Notez le chemin exact depuis la racine
  }
}
