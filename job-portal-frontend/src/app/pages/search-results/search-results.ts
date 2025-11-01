import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { ActivatedRoute } from '@angular/router';
import { JobPostCard } from "../../components/job-post-card/job-post-card";

@Component({
  selector: 'app-search-results',
  imports: [JobPostCard],
  templateUrl: './search-results.html',
  styleUrl: './search-results.scss'
})
export class SearchResults implements OnInit {

  private httpClient = inject(HttpClient);
  private route = inject(ActivatedRoute);

  searchedJobs: any[] = [];

  ngOnInit(): void {
    const keyword = this.route.snapshot.params['searched-keyword'];
    this.httpClient.get<any[]>(`${environment.apiBaseUrl}/jobs/keyword/${keyword}`).subscribe((response) => {
      this.searchedJobs = response;
      console.log(response);
    });
  }

}
