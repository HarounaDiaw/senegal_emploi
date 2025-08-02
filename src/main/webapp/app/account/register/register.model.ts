export class Registration {
  constructor(
    public login: string,
    public email: string,
    public password: string,
    public langKey: string,
    public telephone?: string,
    public adresse?: string,
    public sexe?: string,
    public photo?: string,
    public typeUtilisateur?: 'candidat' | 'recruteur',
    public entreprise?: string,
    public secteur?: string,
  ) {}
}
