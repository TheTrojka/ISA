import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvitationDecisionComponent } from './invitation-decision.component';

describe('InvitationDecisionComponent', () => {
  let component: InvitationDecisionComponent;
  let fixture: ComponentFixture<InvitationDecisionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvitationDecisionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvitationDecisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
