import { ICandidat, NewCandidat } from './candidat.model';

export const sampleWithRequiredData: ICandidat = {
  id: 18880,
};

export const sampleWithPartialData: ICandidat = {
  id: 32579,
  cv: 'lorsque collègue',
  telephone: '0100765498',
  adresse: 'davantage si hirsute',
  photo: 'coac coac au défaut de splendide',
};

export const sampleWithFullData: ICandidat = {
  id: 11710,
  cv: 'dans la mesure où déjà de sorte que',
  telephone: '0726252433',
  adresse: 'observer pin-pon à la merci',
  sexe: 'Masculin',
  photo: 'quand',
};

export const sampleWithNewData: NewCandidat = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
