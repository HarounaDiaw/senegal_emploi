import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { errorRoute } from './layouts/error/error.route';
import { CandidatDashboardComponent } from './candidat-dashboard/candidat-dashboard.component';

const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./home/home.component').then(m => m.default), // Correction clé ici
    title: 'home.title',
  },
  {
    path: '',
    loadComponent: () => import('./layouts/navbar/navbar.component').then(m => m.default),
    outlet: 'navbar',
  },
  {
    path: 'admin',
    data: {
      authorities: [Authority.ADMIN],
    },
    canActivate: [UserRouteAccessService],
    loadChildren: () => import('./admin/admin.routes'),
  },
  {
    path: 'account',
    loadChildren: () => import('./account/account.route'),
  },
  {
    path: 'login',
    loadComponent: () => import('./login/login.component').then(m => m.default),
    title: 'login.title',
  },
  {
    path: '',
    loadChildren: () => import(`./entities/entity.routes`),
  },

  {
    path: 'candidat-dashboard',
    component: CandidatDashboardComponent,
    data: {
      authorities: ['ROLE_CANDIDAT'],
      pageTitle: 'Tableau de bord Candidat',
    },
    canActivate: [UserRouteAccessService],
  },
  ...errorRoute,
];

export default routes;
