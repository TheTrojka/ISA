import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropsAdsComponent } from './props-ads.component';

describe('PropsAdsComponent', () => {
  let component: PropsAdsComponent;
  let fixture: ComponentFixture<PropsAdsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropsAdsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropsAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
