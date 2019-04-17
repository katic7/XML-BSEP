export class SignUpInfo {
    name: string;
    surname: string;
    address: string;
    postalCode: number;
    email: string;
    password: string;
    confirmPassword: string;
    
    constructor(name: string, surname:string, address:string, postalCode:number, email: string, password: string, confirmPassword: string) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}