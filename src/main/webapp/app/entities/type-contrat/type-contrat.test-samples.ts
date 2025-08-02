import { ITypeContrat, NewTypeContrat } from './type-contrat.model';

export const sampleWithRequiredData: ITypeContrat = {
  id: 19173,
  libelle: 'main-d’œuvre précisément',
};

export const sampleWithPartialData: ITypeContrat = {
  id: 18016,
  libelle: 'porte-parole',
};

export const sampleWithFullData: ITypeContrat = {
  id: 1815,
  libelle: 'quitte à rédaction',
};

export const sampleWithNewData: NewTypeContrat = {
  libelle: 'd’autant que ignorer',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
