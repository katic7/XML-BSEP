import { Injectable } from "@angular/core";
import { TokenStorageService } from "./token-storage/token-storage.service";

@Injectable()
export class FrontInterceptor  {

        private roles : Array<any>;

        constructor(private token : TokenStorageService) {}

        checkAuth(role) : boolean{
            this.roles = this.token.getAuthorities();
            if(this.roles.includes(role)) {
               
                return true;
            }
            return false;
        }
}