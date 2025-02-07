import {Resume, Experience } from "../models/resume"

const resume: Resume = {
    name: "Francesc López Marió",
    contact: "rfranr@gmail.com",
    summary: "Software Engineer with a passion for JavaScript and TypeScript. I have a strong foundation in web development, focusing on frontend technologies.",
    education: [
        {
            institution: "Barcelona Business School",
            degree: "Master's degree in Computer Science",
            startDate: new Date("2015-09-01"),
            endDate: new Date("2017-06-30"),
            fieldOfStudy: "Computer Science"
        },
        {
            institution: "Barcelona Business School",
            degree: "Master's degree in Computer Science",
            startDate: new Date("2015-09-01"),
            endDate: new Date("2017-06-30"),
            fieldOfStudy: "Computer Science"
        },
        {
            institution: "Barcelona Business School",
            degree: "Master's degree in Computer Science",
            startDate: new Date("2015-09-01"),
            endDate: new Date("2017-06-30"),
            fieldOfStudy: "Computer Science"
        }
    ],
    skills: ["JavaScript", "TypeScript", "React", "Node.js", "Redux", "CSS", "HTML"],

    languages: [
        {
            language: "English",
            proficiency: "Native"
        },
        {
            language: "Spanish",
            proficiency: "Bilingual"
        },
        {
            language: "Catalan",
            proficiency: "Basic"
        }
    ]
    
    experience: [
        {
            company: "Ripple Labs",
            position: "Software Engineer",
            startDate: new Date("2020-01-01"),
            endDate: new Date("2021-12-31"),
            location: "Barcelona, Spain",
            description: "I've been working at Ripple Labs as a Software Engineer, focusing primarily on frontend development. I've developed various projects, including a React application for managing and visualizing blockchain transactions, and a TypeScript library for interacting with the Ripple API.",
            highlights: ["Developed a React application with Redux for managing blockchain transactions", "Built a TypeScript library for interacting with the Ripple API"],
            achievements: [],
            responsibilities: [],
            projects: ["https://github.com/ripplelabs/ripple-transaction-visualizer", "https://github.com/ripplelabs/ripple-lib-typescript"]
        },
        {
            company: "Vanguard",
            position: "Software Engineer",
            startDate: new Date("2018-01-01"),
            endDate: new Date("2020-01-01"),
            location: "Barcelona, Spain",
            description: "I've been working at Vanguard as a Software Engineer, focusing primarily on backend development. I've developed various projects, including a Node.js application for managing and analyzing financial data, and a TypeScript library for interacting with the Vanguard API.",
            highlights: ["Developed a Node.js application with TypeScript for managing and analyzing financial data", "Built a TypeScript library for interacting with the Vanguard API"],
            achievements: [],
            responsibilities: [],
        },
        {
            company: "Vanguard",
            position: "Software Engineer Intern",
            startDate: new Date("2017-08-01"),
            endDate: new Date("2017-12-31"),
            location: "Barcelona, Spain",
            description: "I've been working at Vanguard as a Software Engineer Intern, focusing primarily on frontend development. I've developed various projects, including a React application for managing and visualizing financial data, and a TypeScript library for interacting with the Vanguard API.",
            highlights: ["Developed a React application with Redux for managing and visualizing financial data", "Built a TypeScript library for interacting with the Vanguard API"],
            achievements: [],
            responsibilities: [],
        }
    ]
}