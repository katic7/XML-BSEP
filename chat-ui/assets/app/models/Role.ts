export class Role {
    id:number;
    name:RoleName;
 }

export enum RoleName {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_AGENT,    
}