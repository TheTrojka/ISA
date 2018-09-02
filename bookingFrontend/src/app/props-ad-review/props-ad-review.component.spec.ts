import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropsAdReviewComponent } from './props-ad-review.component';

describe('PropsAdReviewComponent', () => {
  let component: PropsAdReviewComponent;
  let fixture: ComponentFixture<PropsAdReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropsAdReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropsAdReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
