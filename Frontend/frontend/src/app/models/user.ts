// tslint:disable-next-line:class-name
export class User{
    firstName!: string;
    lastName!: string;
    email!: string;
    dateOfJoining!: Date;

    constructor(firstName: string, lastName: string, email: string, dateOfJoining: Date){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
    }
  }
  