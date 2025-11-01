import { Component, inject, OnInit } from '@angular/core';
import { JobPostCard } from '../../components/job-post-card/job-post-card';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-home',
  imports: [JobPostCard],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home implements OnInit {

  jobs: any[] = [];

  private httpClient = inject(HttpClient);

  ngOnInit(){
    this.httpClient.get(`${environment.apiBaseUrl}/jobs`).subscribe((response: any) => {
      this.jobs = response;
    });
  }

}
