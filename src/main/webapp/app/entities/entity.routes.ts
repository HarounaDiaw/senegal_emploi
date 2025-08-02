import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'candidat',
    data: { pageTitle: 'Candidats' },
    loadChildren: () => import('./candidat/candidat.routes'),
  },
  {
    path: 'recruteur',
    data: { pageTitle: 'Recruteurs' },
    loadChildren: () => import('./recruteur/recruteur.routes'),
  },
  {
    path: 'type-contrat',
    data: { pageTitle: 'TypeContrats' },
    loadChildren: () => import('./type-contrat/type-contrat.routes'),
  },
  {
    path: 'poste',
    data: { pageTitle: 'Postes' },
    loadChildren: () => import('./poste/poste.routes'),
  },
  {
    path: 'localisation',
    data: { pageTitle: 'Localisations' },
    loadChildren: () => import('./localisation/localisation.routes'),
  },
  {
    path: 'offre-emploi',
    data: { pageTitle: 'OffreEmplois' },
    loadChildren: () => import('./offre-emploi/offre-emploi.routes'),
  },
  {
    path: 'candidature',
    data: { pageTitle: 'Candidatures' },
    loadChildren: () => import('./candidature/candidature.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
