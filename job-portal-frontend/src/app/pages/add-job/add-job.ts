import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobPost } from '../../models/JobPost';
import { Auth } from '../../services/auth';
import { environment } from '../../../environments/environment';
import { JobDetails } from '../job-details/job-details';

@Component({
  selector: 'app-add-job',
  imports: [ReactiveFormsModule],
  templateUrl: './add-job.html',
  styleUrl: './add-job.scss',
})
export class AddJob implements OnInit {

  private httpClient = inject(HttpClient);
  private authService = inject(Auth);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  jobId: number | null = null;
  isEditMode: boolean = false;

  ngOnInit(): void {
    if(!this.authService.checkLoggedIn() || this.authService.getUserRole() !== 'EMPLOYER') {
      this.router.navigate(['/login']);
    }

    const paramId = this.route.snapshot.paramMap.get('id');
    if(paramId){
      this.jobId = Number(paramId);
      this.isEditMode = true;

      this.httpClient.get<JobPost>(`${environment.apiBaseUrl}/job/${this.jobId}`).subscribe({
        next: (job) => {
          this.jobObj.setValue({
            jobId: job.jobId,
            jobProfile: job.jobProfile,
            jobDesc: job.jobDesc,
            requiredExperience: job.requiredExperience,
            techStack: job.techStack.join(', ')
          })
        },
        error: (err) => {
          console.error('Failed to load job details', err);
        }
      });
    }
  }

  protected submittedJob: JobPost | null = null;

  jobObj = new FormGroup({
    jobId: new FormControl(0, Validators.required),
    jobProfile: new FormControl('', Validators.required),
    jobDesc: new FormControl('', [Validators.required, Validators.minLength(10)]),
    requiredExperience: new FormControl(0, [Validators.required, Validators.min(0)]),
    techStack: new FormControl('', Validators.required)
  });

  techStackCount(): number {
    const value = this.jobObj.get('techStack')?.value || '';
    return value.split(',')
                .map((x: string) => x.trim())
                .filter((x: string) => x).length;
  }

  onSubmit() {
    if (this.jobObj.valid) {
      const formValue = this.jobObj.value;

      const techStackArray = (formValue.techStack ?? '')
                              .split(',')
                              .map((tech: string) => tech.trim())
                              .filter((tech: string) => tech.length > 0);
      
      const jobPost = {
        ...formValue,
        techStack: techStackArray
      };
      
      const request$ = this.isEditMode?
      this.httpClient.put(`${environment.apiBaseUrl}/job/update/${this.jobId}`, jobPost) :
      this.httpClient.post(`${environment.apiBaseUrl}/job`, jobPost);

      request$.subscribe((response: any) => {
        this.submittedJob = response;
        console.log(this.isEditMode? 'Job updated successfully:' : 'Job posted successfully:', response);
        this.jobObj.reset();
      }, (error) => {
        console.log('Failed to post job:', error);
      });
      
    }
  }

}
