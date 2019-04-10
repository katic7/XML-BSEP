import { Role } from './Role';

export class User {

    constructor(public name:string,
                public username:string,
                public email:string,
                public password:string,
                public rolesDTO:Array<Role>) {}
}