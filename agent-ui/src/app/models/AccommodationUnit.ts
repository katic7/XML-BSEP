import { AccUnitPrice } from './AccUnitPrice';
import { Reservations } from './Reservations';
import { AdditionalService } from './AdditionalService';

export class AccommodationUnit {

    id:number;
    numberOfBeds:number;
    balcony:boolean;
    price:AccUnitPrice;
    description:number;
    rating:number;
    image:string;
    accObjectId:number;
    reservations:Array<Reservations>;
    additionalServices:Array<AdditionalService>;
    
}