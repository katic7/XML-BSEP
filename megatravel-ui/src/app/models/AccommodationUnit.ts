import { AccommodationObject } from 'src/app/models/AccommodationObject';
import { AccUnitPrice } from 'src/app/models/AccUnitPrice';
import { AdditionalService } from 'src/app/models/AdditionalService';

export class AccommodationUnit {

    public id : number;
    public balcony : boolean;
    public description : string;
    public numberOfBeds : number;
    public rating : number;
    public accommodationObject : AccommodationObject;
    public price : AccUnitPrice;
    public additionalServices : AdditionalService[];
    public image : string;
}