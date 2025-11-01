import { HttpClient } from '@angular/common/http';
import { Component, inject, signal, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { ActivatedRoute, Router } from '@angular/router';
import { JobPost } from '../../models/JobPost';
import { Auth } from '../../services/auth';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JobApplication } from '../../models/JobApplication';

@Component({
  selector: 'app-job-details',
  imports: [ReactiveFormsModule],
  templateUrl: './job-details.html',
  styleUrl: './job-details.scss'
})
export class JobDetails implements OnInit {

  private httpClient = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  protected authService = inject(Auth);

  private jobId: number = 0;
  job = signal<JobPost | null>(null);

  protected submittedApplication: JobApplication | null = null;

  applyForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    coverLetter: new FormControl(''), // optional
    resume: new FormControl<File | null>(null, Validators.required)
  });
  resumeFile?: File;
  
  ngOnInit(): void {
    if(!this.authService.checkLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    this.jobId = Number(this.route.snapshot.params['id']);
    
    this.httpClient.get(`${environment.apiBaseUrl}/job/${this.jobId}`).subscribe((response: any) => {
      this.job.set(response as JobPost);
      console.log(response);
    });
  }

  onFileSelect(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    if (element.files && element.files.length > 0) {
      this.resumeFile = element.files[0];
      this.applyForm.patchValue({ resume: this.resumeFile });
    }
  }

  onSubmit() {
    if (this.applyForm.invalid) {
      this.applyForm.markAllAsTouched();
      return;
    }

    const formData = new FormData();
    formData.append('name', this.applyForm.get('name')?.value ?? '');
    formData.append('email', this.applyForm.get('email')?.value ?? '');
    const coverLetter = this.applyForm.get('coverLetter')?.value;
    if (coverLetter) formData.append('coverLetter', coverLetter);
    if (this.resumeFile) formData.append('resume', this.resumeFile);

    this.applyForm.reset();

    this.httpClient.post(`${environment.apiBaseUrl}/apply/${this.jobId}`, formData, { responseType: 'text' }).subscribe({
      next: (res: any) => this.submittedApplication = res,
      error: err => alert('Application failed: ' + err.error)
    });
  }

}
