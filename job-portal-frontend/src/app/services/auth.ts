import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { getCookie } from '../utils/cookies-utils';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  
  private httpClient = inject(HttpClient);
  private router = inject(Router);

  signup(data: any) {
    return this.httpClient.post(`${environment.apiBaseUrl}/register`, data);
  }

  login(data: any) {
    return this.httpClient.post(`${environment.apiBaseUrl}/login`, data, { responseType: "json" });
  }

  logout(){
    document.cookie = `token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
    document.cookie = `role=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
    this.router.navigate(['/login']);
  }

  checkLoggedIn(){
    return document.cookie.includes('token=');
  }

  getUserRole(): string | null {
    return getCookie(environment.role);
  }

}
