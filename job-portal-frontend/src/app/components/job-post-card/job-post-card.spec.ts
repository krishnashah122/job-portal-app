import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobPostCard } from './job-post-card';

describe('JobPostCard', () => {
  let component: JobPostCard;
  let fixture: ComponentFixture<JobPostCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobPostCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobPostCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
