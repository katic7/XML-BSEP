import { Address } from './Address';
import { Category } from './Category';
import { Type } from './Type';

export class AccommodationObject {

    public id : number;
    public address : Address;
    public category : Category;
    public daysToCancel : number;
    public name : string;
    public description : string;
    public freeCancelation : number;
    public type : Type;
}