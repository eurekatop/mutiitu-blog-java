export interface Resume {
    name: string;
    summary: string;
    contact: string;
    skills: string[];
    experience: Experience[];
    education: Education[];
    projects: Project[];
    achievements: Achievement[];
    hobbies: string[];
    languages: {language:string, proficiency:string}[];
    references: Reference[];
    certifications: Certification[];
    publications: Publication[];
    awards: Award[];
    interests: Interest[];
    honors: string[];
    educationalBackground: string;  
    additionalInformation: string;
}

export interface Publication {
    title: string;
    author: string;
    publicationDate: Date;
    publisher: string;
    url: string;
}

export interface Award {
    title: string;
    date: Date;
    url: string;
    type: string;
    location: string;
    institution: string;
}

export interface Reference {
    name: string;
    title: string;
    email: string;
    phone: string;
    address: string;
    city: string;
    description: string
}

export interface Achievement {
    title: string;
    description: string;
    date: Date;
    url: string;   
}

export interface Project {
    title: string;
    description: string;
    startDate: Date;
    endDate: Date;
    url: string;
    technologies: string[];
    role: string;
    accomplishments: string[];
    links: string[];
    screenshots: string[];
}

export interface Education {
    institution: string;
    degree: string;
    startDate: Date;
    endDate: Date;
    fieldOfStudy: string;
}

export interface Certification {
    institution: string;
    certification: string;
    date: Date;
    details: string;
}

export interface Interest {
    interest: string;
    description: string;
    projects: string[];
}


export interface Experience {
    company: string;
    position: string;
    startDate: Date;
    endDate: Date;
    location: string;
    description: string;
    highlights: string[];
    achievements: string[];
    responsibilities: string[];
    projects?: string[];  
}

