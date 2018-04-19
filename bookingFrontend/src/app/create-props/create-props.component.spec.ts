import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePropsComponent } from './create-props.component';

describe('CreatePropsComponent', () => {
  let component: CreatePropsComponent;
  let fixture: ComponentFixture<CreatePropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
