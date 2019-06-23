export class ReservationDTO {

    constructor(public id:number,
                public userId:number,
                public accommodationUnitId:number,
                public price:number,
                public beginDate:string,
                public endDate:string,
                public completed:boolean) {}
}