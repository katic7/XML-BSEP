export class Role {
    id:number;
    name:RoleName;
 }

export enum RoleName {
    ROLE_ADMIN,
    ROLE_AGENT,
    ROLE_USER,
    ROLE_SYSTEM_ADMIN,    
}