import { TestBed, inject } from '@angular/core/testing';

import { PropsAdService } from './props-ad.service';

describe('PropsAdService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PropsAdService]
    });
  });

  it('should be created', inject([PropsAdService], (service: PropsAdService) => {
    expect(service).toBeTruthy();
  }));
});
