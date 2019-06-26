import { AccommodationObject } from './AccommodationObject';
import { User } from './User';

export class Agent extends User{
    public pib : String;
    public accObj : AccommodationObject;   
}
