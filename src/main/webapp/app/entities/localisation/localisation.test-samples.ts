import { ILocalisation, NewLocalisation } from './localisation.model';

export const sampleWithRequiredData: ILocalisation = {
  id: 24192,
  region: 'vroum sans que',
  departement: 'écraser vis-à-vie de',
};

export const sampleWithPartialData: ILocalisation = {
  id: 20312,
  region: 'solitaire autrement bien que',
  departement: 'fourbe juriste dessus',
};

export const sampleWithFullData: ILocalisation = {
  id: 26734,
  region: 'psitt baigner tellement',
  departement: 'membre titulaire',
};

export const sampleWithNewData: NewLocalisation = {
  region: 'corps enseignant',
  departement: 'cocorico lorsque avaler',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
