import dayjs from 'dayjs/esm';

import { IOffreEmploi, NewOffreEmploi } from './offre-emploi.model';

export const sampleWithRequiredData: IOffreEmploi = {
  id: 11534,
  titre: 'en face de',
  description: 'avant que',
};

export const sampleWithPartialData: IOffreEmploi = {
  id: 28713,
  titre: 'afin de',
  description: 'commis',
  remuneration: 767.13,
  datePublication: dayjs('2025-08-01T06:11'),
};

export const sampleWithFullData: IOffreEmploi = {
  id: 19852,
  titre: 'fumer ha ha',
  description: 'incalculable puisque',
  remuneration: 16229.21,
  datePublication: dayjs('2025-08-01T14:50'),
  affiche: 'ronron',
};

export const sampleWithNewData: NewOffreEmploi = {
  titre: 'croâ à même parce que',
  description: 'depuis afin que sincère',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
