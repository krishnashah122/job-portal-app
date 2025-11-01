import { Component, inject, input } from '@angular/core';
import { JobPost } from '../../models/JobPost';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../services/auth';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-job-post-card',
  imports: [RouterModule],
  standalone: true,
  templateUrl: './job-post-card.html',
  styleUrl: './job-post-card.scss'  
})
export class JobPostCard {

  private authService = inject(Auth);
  private httpClient = inject(HttpClient);
  private router = inject(Router);
  protected userRole: string = this.authService.getUserRole() || '';

  job = input<JobPost>();

  onUpdate(jobId: number | undefined) {
    this.router.navigate([`/job/update`, jobId]);
  }

  deleteJob() {
    this.httpClient.delete(`${environment.apiBaseUrl}/job/${this.job()?.jobId}`, { responseType: 'text' }).subscribe((response: any) =>{
      alert(response);
      console.log(response);
      window.location.reload();
    });
  }

}
