import { Role } from './Role';

export class User {

    constructor(public id:number,
                public name:string,
                public surname:string,
                public address:string,
                public postalCode:number,
                public email:string,
                public telephone:string,
                public password:string,
                public enabled:boolean,
                public nonLocked:boolean,
                public rolesDTO:Array<Role>) {}
}