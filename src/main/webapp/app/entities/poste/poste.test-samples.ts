import { IPoste, NewPoste } from './poste.model';

export const sampleWithRequiredData: IPoste = {
  id: 21776,
  nom: 'de peur que solliciter',
};

export const sampleWithPartialData: IPoste = {
  id: 1948,
  nom: 'tout à fait',
};

export const sampleWithFullData: IPoste = {
  id: 17229,
  nom: 'atchoum cogner',
};

export const sampleWithNewData: NewPoste = {
  nom: 'comprendre',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
