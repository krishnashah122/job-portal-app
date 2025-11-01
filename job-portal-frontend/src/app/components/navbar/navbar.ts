import { Component, Inject, inject, input } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { Auth } from '../../services/auth';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, FormsModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar {

  protected authService = inject(Auth);
  private router = inject(Router);
  protected userRole: string = this.authService.getUserRole() || '';

  searchKeyword: string = '';
  

  onSearch(){
    this.router.navigate([`/job/keyword/${this.searchKeyword}`]);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
