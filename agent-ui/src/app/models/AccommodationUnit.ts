import { Price } from './Price';
import { Reservations } from './Reservations';
import { AdditionalService } from './AdditionalService';

export class AccommodationUnit {

    id:number;
    numberOfBeds:number;
    balcony:boolean;
    price:Price;
    description:number;
    rating:number;
    image:string;
    accObjectId:number;
    reservations:Array<Reservations>;
    additionalServices:Array<AdditionalService>;
    
}