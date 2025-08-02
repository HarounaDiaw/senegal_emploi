import dayjs from 'dayjs/esm';

import { ICandidature, NewCandidature } from './candidature.model';

export const sampleWithRequiredData: ICandidature = {
  id: 28558,
};

export const sampleWithPartialData: ICandidature = {
  id: 13073,
  dateDepot: dayjs('2025-08-01T06:02'),
};

export const sampleWithFullData: ICandidature = {
  id: 23153,
  dateDepot: dayjs('2025-08-01T23:32'),
  statut: 'ferme après aussi',
};

export const sampleWithNewData: NewCandidature = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
