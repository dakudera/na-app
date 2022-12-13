package tech.na_app.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.na_app.entity.role_buttons.Button;
import tech.na_app.entity.role_buttons.RoleButtons;
import tech.na_app.model.enums.ButtonType;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.repository.RoleButtonsRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartUpService {

    private final RoleButtonsRepository roleButtonsRepository;


    @PostConstruct
    public void setUp() {
        fillRoleButtons();
    }

    private void fillRoleButtons() {
        Optional<RoleButtons> byRole = roleButtonsRepository.findByRole(UserRoleType.CHIEF_ACCOUNTANT);
        if (byRole.isEmpty()) {
            List<RoleButtons> roleButtons = new ArrayList<>();
            List<Button> allowed_buttons = new ArrayList<>();
            allowed_buttons.add(
                    Button.builder()
                            .title("Головна")
                            .button(ButtonType.MAIN)
                            .rote("/main")
                            .build()
            );
            allowed_buttons.add(
                    Button.builder()
                            .title("Транспорт")
                            .button(ButtonType.TRANSPORT)
                            .rote("/main/cars/table")
                            .build()
            );
            allowed_buttons.add(
                    Button.builder()
                            .title("Компанії")
                            .button(ButtonType.COMPANY)
                            .rote("/main/company/company-list")
                            .build()
            );
            allowed_buttons.add(
                    Button.builder()
                            .title("Адміністрування")
                            .button(ButtonType.ADMINISTRATION)
                            .rote("/main/admin/admin-dashboard")
                            .build()
            );
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.SUPER_ADMIN)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.ADMIN)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.DIRECTOR)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.EXECUTIVE_DIRECTOR)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.CHIEF_ACCOUNTANT)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtons.add(RoleButtons.builder()
                    .role(UserRoleType.UNKNOWN)
                    .allowed_buttons(allowed_buttons)
                    .build());
            roleButtonsRepository.saveAll(roleButtons);
        }

    }

}
