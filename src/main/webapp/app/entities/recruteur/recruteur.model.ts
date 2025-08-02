export interface IRecruteur {
  id: number;
  entreprise?: string | null;
  secteur?: string | null;
}

export type NewRecruteur = Omit<IRecruteur, 'id'> & { id: null };
