import { AccommodationObject } from './AccommodationObject';
import { User } from './User';

export class Agent extends User{
    private pib : String;
    private accObj : AccommodationObject;   
}
