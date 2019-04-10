export class SignUpInfo {
    name: string;
    username: string;
    email: string;
    password: string;
    role: string[];
    
    constructor(name: string, username:string, email: string, password: string) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ['ROLE_USER'];
    }
}