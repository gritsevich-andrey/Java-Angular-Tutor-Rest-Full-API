export interface User {
  email?: string;
  password?: string;
  returnSecureToken?: boolean;
}

export class Users {
  userId?: string;
  email?: string | any;
  roles?: string[];
  profile?: UserProfile;
}

export class Instructors {
  userId?: string;
  email?: string;
  roles?: string;
  profile?: UserProfile;
}

export interface Lesson {
  instructorId?: string;
  pupilId: string;
  instructorName: string;
  date: string;
  time?: string;
  cost: number;
  held: boolean;
}

export interface Filter {
  start?: Date;
  end?: Date;
  lesson?: number;
}

export interface CategoryProgram {
  id: string;
  name: string;
  imageSrc: string;
}

export interface Program {
  id?: string;
  name: string | any;
  imageSrc?: string;
  instructorId?: string;
  instructorName?: string;
  cost: string;
  category: string | any;
  description?: string;
}

export interface UserProfile {
  name?: string;
  surname?: string;
  tel?: string;
  description?: string;
}
export interface BuyingProgram {
  customerId?: string;
  name: string;
  imageSrc: string;
  description: string;
  instructorId: string;
  instructorName: string;
  category: string;
  cost: number;
  payment: boolean;
  date?: Date;
}
