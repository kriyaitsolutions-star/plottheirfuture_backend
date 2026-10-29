package com.plottheirfuture.plottheirfuture_backend.config;

import com.plottheirfuture.plottheirfuture_backend.entity.Ceremony;
import com.plottheirfuture.plottheirfuture_backend.entity.Contribution;
import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanTile;
import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanZone;
import com.plottheirfuture.plottheirfuture_backend.entity.Invitation;
import com.plottheirfuture.plottheirfuture_backend.entity.Location;
import com.plottheirfuture.plottheirfuture_backend.entity.PaymentStatus;
import com.plottheirfuture.plottheirfuture_backend.entity.RsvpStatus;
import com.plottheirfuture.plottheirfuture_backend.entity.TaxReceipt;
import com.plottheirfuture.plottheirfuture_backend.entity.TileStatus;
import com.plottheirfuture.plottheirfuture_backend.entity.User;
import com.plottheirfuture.plottheirfuture_backend.entity.WebApp;
import com.plottheirfuture.plottheirfuture_backend.entity.WebAppType;
import com.plottheirfuture.plottheirfuture_backend.entity.Wedding;
import com.plottheirfuture.plottheirfuture_backend.entity.WeddingStatus;
import com.plottheirfuture.plottheirfuture_backend.repository.CeremonyRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.ContributionRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.FloorPlanTileRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.FloorPlanZoneRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.InvitationRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.LocationRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.TaxReceiptRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.UserRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.WebAppRepository;
import com.plottheirfuture.plottheirfuture_backend.repository.WeddingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final WebAppRepository webAppRepository;
    private final WeddingRepository weddingRepository;
    private final CeremonyRepository ceremonyRepository;
    private final InvitationRepository invitationRepository;
    private final FloorPlanZoneRepository floorPlanZoneRepository;
    private final FloorPlanTileRepository floorPlanTileRepository;
    private final ContributionRepository contributionRepository;
    private final TaxReceiptRepository taxReceiptRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedLocationsAndUsers();
        seedWebApps();
        seedWeddingsAndCampaignData();
    }

    private void seedLocationsAndUsers() {
        if (userRepository.count() > 0 || locationRepository.count() > 0) {
            log.info("Users/Locations already seeded, skipping dummy data creation.");
            return;
        }

        log.info("Seeding dummy data for Users and Locations...");

        User admin = User.builder()
                .name("Sanvi Reddy")
                .password(passwordEncoder.encode("Admin@123"))
                .mobileNumber("+919848012345")
                .role("ADMIN")
                .otp("123456")
                .roleId(1L)
                .registered(true)
                .image("https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=400&q=80")
                .build();
        admin = userRepository.save(admin);

        User couple = User.builder()
                .name("Santosh Varma")
                .password(passwordEncoder.encode("Couple@123"))
                .mobileNumber("+919876543210")
                .role("COUPLE")
                .otp("654321")
                .roleId(2L)
                .registered(true)
                .image("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=400&q=80")
                .build();
        couple = userRepository.save(couple);

        User guest = User.builder()
                .name("Malli Karjuna")
                .password(passwordEncoder.encode("Guest@123"))
                .mobileNumber("+919988776655")
                .role("GUEST")
                .otp("482910")
                .roleId(3L)
                .registered(false)
                .image("https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=400&q=80")
                .build();
        guest = userRepository.save(guest);

        Location loc1 = locationRepository.save(Location.builder()
                .userId(admin.getId())
                .city("Hyderabad")
                .mandal("Gachibowli")
                .district("Rangareddy")
                .constituency("Serilingampally")
                .pincode("500032")
                .active(true)
                .priority(1)
                .build());

        Location loc2 = locationRepository.save(Location.builder()
                .userId(couple.getId())
                .city("Bengaluru")
                .mandal("Indiranagar")
                .district("Bengaluru Urban")
                .constituency("Shantinagar")
                .pincode("560038")
                .active(true)
                .priority(1)
                .build());

        Location loc3 = locationRepository.save(Location.builder()
                .userId(guest.getId())
                .city("Vijayawada")
                .mandal("Benz Circle")
                .district("Krishna")
                .constituency("Vijayawada East")
                .pincode("520010")
                .active(true)
                .priority(1)
                .build());

        admin.setLocationId(loc1.getId());
        couple.setLocationId(loc2.getId());
        guest.setLocationId(loc3.getId());

        userRepository.saveAll(List.of(admin, couple, guest));
        log.info("Successfully seeded 3 Users and 3 Locations.");
    }

    private void seedWebApps() {
        if (webAppRepository.count() > 0) {
            log.info("WebApp already seeded, skipping dummy data creation.");
            return;
        }

        log.info("Seeding dummy data for WebApp...");

        WebApp mainPortal = WebApp.builder()
                .name("PlotTheirFuture Main Portal")
                .type(WebAppType.website)
                .active(true)
                .primaryColour("#1E3A8A")
                .secondaryColour("#D97706")
                .themeColour("#F8FAFC")
                .heroImages(List.of(
                        "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=1600&q=80",
                        "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=1600&q=80",
                        "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?auto=format&fit=crop&w=1600&q=80"
                ))
                .build();

        WebApp microsite = WebApp.builder()
                .name("Sanvi & Santosh Wedding Microsite")
                .type(WebAppType.app)
                .active(true)
                .primaryColour("#831843")
                .secondaryColour("#F59E0B")
                .themeColour("#FFFBEB")
                .heroImages(List.of(
                        "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=1600&q=80",
                        "https://images.unsplash.com/photo-1606800052052-a08af7148866?auto=format&fit=crop&w=1600&q=80"
                ))
                .build();

        webAppRepository.saveAll(List.of(mainPortal, microsite));
        log.info("Successfully seeded 2 WebApps with hero images.");
    }

    private void seedWeddingsAndCampaignData() {
        if (weddingRepository.count() > 0) {
            log.info("Weddings already seeded, skipping dummy data creation.");
            return;
        }

        log.info("Seeding Wedding campaigns, ceremonies, roster, floor plan, and contributions...");

        // 1. Wedding Campaign
        Wedding wedding = Wedding.builder()
                .slug("sanvi-santosh")
                .brideName("Sanvi Reddy")
                .groomName("Santosh Varma")
                .targetSft(1200)
                .rateSft(4500.0)
                .videoStreamUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .coverImageUrl("https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=1600&q=80")
                .status(WeddingStatus.ACTIVE)
                .builderReraId("PRM/KA/RERA/1251/310/PR/210312/004018")
                .escrowAccountNumber("ESC-HDFC-99201948102")
                .build();
        wedding = weddingRepository.save(wedding);

        // 2. Ceremonies
        Ceremony haldi = ceremonyRepository.save(Ceremony.builder()
                .weddingId(wedding.getId())
                .name("Haldi & Mehendi")
                .eventDate(LocalDate.of(2026, 11, 14))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(14, 0))
                .venueName("The Palm Meadows, Hyderabad")
                .venueMapUrl("https://maps.google.com/?q=Palm+Meadows+Hyderabad")
                .build());

        Ceremony muhurtham = ceremonyRepository.save(Ceremony.builder()
                .weddingId(wedding.getId())
                .name("Wedding Muhurtham")
                .eventDate(LocalDate.of(2026, 11, 15))
                .startTime(LocalTime.of(9, 45))
                .endTime(LocalTime.of(12, 30))
                .venueName("Grand Palace Convention Hall, Hyderabad")
                .venueMapUrl("https://maps.google.com/?q=Grand+Palace+Hyderabad")
                .build());

        Ceremony reception = ceremonyRepository.save(Ceremony.builder()
                .weddingId(wedding.getId())
                .name("Grand Reception")
                .eventDate(LocalDate.of(2026, 11, 15))
                .startTime(LocalTime.of(19, 0))
                .endTime(LocalTime.of(23, 0))
                .venueName("Grand Palace Lawn, Hyderabad")
                .venueMapUrl("https://maps.google.com/?q=Grand+Palace+Hyderabad")
                .build());

        // 3. Guest Invitations Roster
        Invitation inv1 = invitationRepository.save(Invitation.builder()
                .weddingId(wedding.getId())
                .phoneE164("+919988776655")
                .guestName("Malli Karjuna")
                .cohort("FAMILY")
                .ceremonies(List.of("Haldi & Mehendi", "Wedding Muhurtham", "Grand Reception"))
                .refLead("uncle")
                .rsvpStatus(RsvpStatus.ATTENDING)
                .headcount(2)
                .build());

        Invitation inv2 = invitationRepository.save(Invitation.builder()
                .weddingId(wedding.getId())
                .phoneE164("+919123456789")
                .guestName("Rahul Sharma")
                .cohort("COLLEAGUES")
                .ceremonies(List.of("Grand Reception"))
                .refLead("office")
                .rsvpStatus(RsvpStatus.PENDING)
                .headcount(1)
                .build());

        // 4. Floor Plan Zones (1,200 SFT Total)
        FloorPlanZone livingRoom = floorPlanZoneRepository.save(FloorPlanZone.builder()
                .weddingId(wedding.getId())
                .roomName("Living Room")
                .totalSft(400)
                .fundedSft(45)
                .svgElementId("svg_living_room")
                .build());

        FloorPlanZone masterBed = floorPlanZoneRepository.save(FloorPlanZone.builder()
                .weddingId(wedding.getId())
                .roomName("Master Bedroom")
                .totalSft(250)
                .fundedSft(20)
                .svgElementId("svg_master_bed")
                .build());

        FloorPlanZone balcony = floorPlanZoneRepository.save(FloorPlanZone.builder()
                .weddingId(wedding.getId())
                .roomName("Balcony")
                .totalSft(80)
                .fundedSft(15)
                .svgElementId("svg_balcony")
                .build());

        FloorPlanZone kitchen = floorPlanZoneRepository.save(FloorPlanZone.builder()
                .weddingId(wedding.getId())
                .roomName("Kitchen")
                .totalSft(120)
                .fundedSft(10)
                .svgElementId("svg_kitchen")
                .build());

        // 5. Floor Plan Tiles
        FloorPlanTile tile1 = floorPlanTileRepository.save(FloorPlanTile.builder()
                .weddingId(wedding.getId())
                .zoneId(livingRoom.getId())
                .sftSize(5)
                .status(TileStatus.FUNDED)
                .donorId(inv1.getId())
                .build());

        FloorPlanTile tile2 = floorPlanTileRepository.save(FloorPlanTile.builder()
                .weddingId(wedding.getId())
                .zoneId(livingRoom.getId())
                .sftSize(10)
                .status(TileStatus.HOLD)
                .lockedByPhone("+919988776655")
                .lockedUntil(LocalDateTime.now().plusMinutes(10))
                .build());

        FloorPlanTile tile3 = floorPlanTileRepository.save(FloorPlanTile.builder()
                .weddingId(wedding.getId())
                .zoneId(balcony.getId())
                .sftSize(1)
                .status(TileStatus.OPEN)
                .build());

        // 6. Contribution
        Contribution contribution = contributionRepository.save(Contribution.builder()
                .weddingId(wedding.getId())
                .invitationId(inv1.getId())
                .unitsSft(5)
                .amount(22500.0)
                .txnId("TXN_UPI_992019482")
                .bankRef("HDFC0001892837")
                .paymentStatus(PaymentStatus.SUCCESS)
                .isAnonymous(false)
                .blessingMessage("May your new home be filled with endless joy and laughter! - Malli")
                .createdAt(LocalDateTime.now())
                .build());

        // 7. Tax Receipt (Section 56(2)(x))
        taxReceiptRepository.save(TaxReceipt.builder()
                .contributionId(contribution.getId())
                .receiptNo("PTF-SEC-892104")
                .exemptClause("Section 56(2)(x) of Indian Income Tax Act")
                .digitalSignatureHash("9a8fbc83d91e84a92c0182b810948ac019")
                .pdfUrl("https://plottheirfuture.in/receipts/PTF-SEC-892104.pdf")
                .generatedAt(LocalDateTime.now())
                .build());

        log.info("Successfully seeded complete Wedding, Itinerary, Floor Plan, and Escrow data.");
    }
}
