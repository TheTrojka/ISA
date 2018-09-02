import { Establishment } from './establishment';

export class User {
    public id: number;
    public email: string;
    public password: string;
    public name: string;
    public lastname: string;
    public phone: number;
    public city: string;
    public role: string;
    public establishment: number;
    public confirmationToken: string;
    public resNum: number;
}
