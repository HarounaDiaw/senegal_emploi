import { Sexe } from 'app/entities/enumerations/sexe.model';

export interface ICandidat {
  id: number;
  cv?: string | null;
  telephone?: string | null;
  adresse?: string | null;
  sexe?: keyof typeof Sexe | null;
  photo?: string | null;
}

export type NewCandidat = Omit<ICandidat, 'id'> & { id: null };
