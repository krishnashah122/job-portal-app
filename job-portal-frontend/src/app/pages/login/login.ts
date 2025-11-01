import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../services/auth';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login implements OnInit {

  private router = inject(Router);
  private authService = inject(Auth);

  ngOnInit(): void {
    if(this.authService.checkLoggedIn()){
      this.router.navigate(['/']);
    }
  }

  loginObj = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  onSubmit(){
    
    if(this.loginObj.valid){
      this.authService.login(this.loginObj.value).subscribe((response: any) => {
        let jwtToken = response.jwtToken;
        document.cookie = `${environment.token}=${jwtToken}; path=/; max-age=3600; SameSite:Strict; Secure`;
        document.cookie = `${environment.role}=${response.role}; path=/; max-age=3600; SameSite:Strict; Secure`;
        console.log("Logged In Successfully");
        this.router.navigate(['/']);
      });
    }
    
  }

}
