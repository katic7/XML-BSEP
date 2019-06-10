export class Role {
    id:number;
    name:RoleName;
 }

export enum RoleName {
    ROLE_USER,
    ROLE_SYSTEMADMIN,
    ROLE_AGENT,    
}