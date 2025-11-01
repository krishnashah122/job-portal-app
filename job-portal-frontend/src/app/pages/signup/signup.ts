import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-signup',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './signup.html',
  styleUrl: './signup.scss'
})
export class Signup implements OnInit {

  private router = inject(Router);
  private authService = inject(Auth);

  ngOnInit(): void {
    if(this.authService.checkLoggedIn()){
      this.router.navigate(['/']);
    }
  }

  signupObj = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    role: new FormControl('', Validators.required)
  });

  onSubmit(){
    
    if(this.signupObj.valid){
      this.authService.signup(this.signupObj.value).subscribe((response: any) => {
        console.log(response);
        this.router.navigate(['/login']);
      });
    }
    
  }

}
