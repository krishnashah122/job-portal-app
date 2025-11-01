import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { About } from './pages/about/about';
import { Contact } from './pages/contact/contact';
import { Signup } from './pages/signup/signup';
import { Login } from './pages/login/login';
import { NotFound } from './pages/not-found/not-found';
import { JobDetails } from './pages/job-details/job-details';
import { SearchResults } from './pages/search-results/search-results';
import { AddJob } from './pages/add-job/add-job';
import { JobApplications } from './pages/job-applications/job-applications';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'about',
        component: About
    },
    {
        path: 'contact',
        component: Contact
    },
    {
        path: 'signup',
        component: Signup
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'job/:id',
        component: JobDetails
    },
    {
        path: 'job/keyword/:searched-keyword',
        component: SearchResults
    },
    {
        path: 'job',
        component: AddJob
    },
    {
        path: 'job/update/:id',
        component: AddJob
    },
    {
        path: 'applications',
        component: JobApplications
    },
    {
        path: "**",
        component: NotFound
    }
];
