import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropsAdDetailsComponent } from './props-ad-details.component';

describe('PropsAdDetailsComponent', () => {
  let component: PropsAdDetailsComponent;
  let fixture: ComponentFixture<PropsAdDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropsAdDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropsAdDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
