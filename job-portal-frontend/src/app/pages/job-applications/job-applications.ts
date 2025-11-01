import { Component, inject } from '@angular/core';
import { JobApplication } from '../../models/JobApplication';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { DatePipe } from '@angular/common';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-applications',
  imports: [DatePipe],
  templateUrl: './job-applications.html',
  styleUrl: './job-applications.scss'
})
export class JobApplications {

  jobApplications: JobApplication[] = [];
  isLoading = true;
  errorMessage = '';

  httpClient = inject(HttpClient);
  authService = inject(Auth);
  router = inject(Router);

  ngOnInit(): void {
    if(!this.authService.checkLoggedIn() || this.authService.getUserRole() !== 'EMPLOYER') {
      this.router.navigate(['/not-found']);
      return;
    }

    this.httpClient.get<JobApplication[]>(`${environment.apiBaseUrl}/applications`).subscribe({
      next: (data) => {
        this.jobApplications = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load applications.';
        this.isLoading = false;
      }
    });
  }

  showCoverLetter(coverLetter: string) {
    alert(coverLetter);
  }


}
