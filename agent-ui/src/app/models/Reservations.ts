export class Reservations {

    constructor(public id:number,
                public userID:number,
                public reservationDate:string,
                public accUnitId:number,
                public price:number,
                public beginDate:string,
                public endDate:string,
                public completed:boolean,
                public active:boolean) {}
}